package tz.co.mkwanja.africa.controller;

import tz.co.mkwanja.africa.config.jwt.IAuthenticationFacade;
import tz.co.mkwanja.africa.models.Role;
import tz.co.mkwanja.africa.models.User;
import tz.co.mkwanja.africa.models.enums.EPerson;
import tz.co.mkwanja.africa.models.enums.ERole;
import tz.co.mkwanja.africa.payload.request.OwnerInformationRequest;
import tz.co.mkwanja.africa.payload.response.MessageResponse;
import tz.co.mkwanja.africa.payload.response.UserResponse;
import tz.co.mkwanja.africa.repository.RoleRepository;
import tz.co.mkwanja.africa.repository.UserRepository;
import tz.co.mkwanja.africa.service.FileStorageService;
import tz.co.mkwanja.africa.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @PostMapping("/update_profile_picture")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateProfilePicture(@Valid @RequestPart("username") String username, @RequestPart("img") MultipartFile img) {
        if (!userRepository.existsByPhone(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(101, "Error: User not Found", "Mtumiaji hajapatikana.", ""));
        }

        User user = userRepository.findByPhone(username).get();
        //remove current image if there is one
        if (user.getProfile() != null) {
            fileStorageService.removePreviousImageIFExists(user.getProfile());
        }
        user.setProfile(fileStorageService.saveImage(img));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse(200, "User Picture Updated successfully!", "Picha imesahihishwa", new UserResponse(user)));
    }


    @PostMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@Valid @RequestPart("firstname") @Min(3) @Max(20) String firstname,
                                        @RequestPart("lastname") @Min(3) @Max(20) String lastname,
                                        @RequestPart("phone") @Max(12) String phone,
                                        @RequestPart(value = "email", required = false) @Max(50) String email,
                                        @RequestPart("roles") String rolesValue,
                                        @RequestPart(value = "userId", required = false) HashMap<String, String> userId,
                                        @RequestPart(value = "img", required = false) MultipartFile img) {
        if (!userRepository.existsByPhone(phone)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(101, "Error: User not Found", "Mtumiaji hajapatikana.", ""));
        }

        if (!email.equals("null")) {
            if (!userRepository.existsByEmail(email)) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse(101, "Error: User not Found", "Mtumiaji hajapatikana", ""));
            }
        }


        // Update user's account
        User user = userRepository.findByPhone(phone).get();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        if (!email.equals("null")) {
            user.setEmail(email);
        }
        user.setUserId(userId);

        if (userId != null) {
            user.setUserId(userId);
        }
        //save Profile picture if is set
        if (img != null) {

            //remove current image if there is one
            if (user.getProfile() != null) {
                fileStorageService.removePreviousImageIFExists(user.getProfile());
            }
            user.setProfile(fileStorageService.saveImage(img));
        }

        rolesValue = rolesValue.replace("[", "");
        rolesValue = rolesValue.replace("]", "");
        rolesValue = rolesValue.replace('"', '#');
        rolesValue = rolesValue.replace("#", "");

        List<String> roleList = Arrays.asList(rolesValue.split(","));

        Set<String> strRoles = new HashSet<>(roleList);
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "ROLE_SUPPLIER":
                        Role modRole = roleRepository.findByName(ERole.ROLE_SUPPLIER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    case "ROLE_RETAILER":
                        Role retailerRole = roleRepository.findByName(ERole.ROLE_RETAILER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(retailerRole);

                        break;
                    case "ROLE_DISTRIBUTOR":
                        Role disitributorRole = roleRepository.findByName(ERole.ROLE_DISTRIBUTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(disitributorRole);

                        break;
                    case "ROLE_STOREKEEPER":
                        Role storekeerRole = roleRepository.findByName(ERole.ROLE_STOREKEEPER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(storekeerRole);

                        break;
                    case "ROLE_SALES":
                        Role salesRole = roleRepository.findByName(ERole.ROLE_SALES)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(salesRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse(200, "User Updated successfully!", "Mtumiaji amesahihishwa", new UserResponse(user)));
    }

    @PostMapping("/update/v2")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUserV2(@Valid @RequestBody OwnerInformationRequest request) {

        // Update user's account
        User user = userRepository.findById(getUser().getId()).get();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());

        if (request.getEmail() != null) {
            if (userRepository.existsByEmail(request.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse(101, "Error: Email is already in use!", "Email imeshatumika", ""));
            } else {
                user.setEmail(request.getEmail());
            }
        }

        //save Profile picture if is set
//        if (img != null) {
//
//            //remove current image if there is one
//            if (user.getProfile() != null) {
//                fileStorageService.removePreviousImageIFExists(user.getProfile());
//            }
//            user.setProfile(fileStorageService.saveImage(img));
//        }


        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse(200, "User Updated successfully!", "Mtumiaji amesahihishwa", new UserResponse(user)));
    }

    @RequestMapping("/list")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN')")
    public ResponseEntity<Object> list(@RequestParam(value = "role", required = false) String role) {
        List<UserResponse> list = new ArrayList<>();
        if (role == null || role.isEmpty()) {
            list = userRepository.findAll().stream().map(user -> new UserResponse(
                    user.getId(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getRoles(),
                    user.getProfile(), user.getUserId(),
                    user.getStatus()))
                    .collect(Collectors.toList());
        } else {
            list = userRepository.findAll().stream()
                    .filter(user -> {
                        for (Role r : user.getRoles()) {
                            if (r.getName().toString().contains(role)) {
                                return true;
                            }
                        }
                        return false;
                    })
                    .map(user -> new UserResponse(
                            user.getId(),
                            user.getFirstname(),
                            user.getLastname(),
                            user.getPhone(),
                            user.getEmail(),
                            user.getRoles(),
                            user.getProfile(), user.getUserId(),
                            user.getStatus()))
                    .collect(Collectors.toList());
        }


        if (list.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse(404, "You have no User added yet", "Hakuna mtumiaji aliyeongezwa", ""));
        }
        return ResponseEntity.ok(list);
    }

    @RequestMapping("/details")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> detail() {

        User user = userRepository.findById(getUser().getId()).get();
        UserResponse response = new UserResponse(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhone(),
                user.getEmail(),
                user.getRoles(),
                user.getProfile(), user.getUserId(),
                user.getStatus());

        List<String> roles = getUser().getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@Valid @RequestParam("id") String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getStatus().equals(EPerson.ACTIVE.toString())) {
                user.setStatus(EPerson.BLOCKED.toString());
            } else {
                user.setStatus(EPerson.ACTIVE.toString());
            }
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse(200, "User status changed successfully", "Mtumiaji kasahihishwa", new UserResponse(user)));
        }
        return ResponseEntity.badRequest().body(new MessageResponse(199, "Failed to find user with ID " + id, "Imeshindikana kupata mtumiaji mwenye utambulisho " + id, ""));
    }

    private UserDetailsImpl getUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }
}

