package tz.co.mkwanja.africa.controller;

import tz.co.mkwanja.africa.config.jwt.JwtUtils;
import tz.co.mkwanja.africa.exceptions.AccessDeniedException;
import tz.co.mkwanja.africa.exceptions.ResourceNotFoundException;
import tz.co.mkwanja.africa.helpers.Constants;
import tz.co.mkwanja.africa.models.Role;
import tz.co.mkwanja.africa.models.User;
import tz.co.mkwanja.africa.models.enums.EPerson;
import tz.co.mkwanja.africa.models.enums.ERole;
import tz.co.mkwanja.africa.payload.request.AppLoginRequest;
import tz.co.mkwanja.africa.payload.request.AppUserVerification;
import tz.co.mkwanja.africa.payload.request.LoginRequest;
import tz.co.mkwanja.africa.payload.response.JwtResponse;
import tz.co.mkwanja.africa.payload.response.MessageResponse;
import tz.co.mkwanja.africa.payload.response.UserResponse;
import tz.co.mkwanja.africa.repository.RoleRepository;
import tz.co.mkwanja.africa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tz.co.mkwanja.africa.service.FileStorageService;
import tz.co.mkwanja.africa.service.SmsService;
import tz.co.mkwanja.africa.service.UserDetailsImpl;
import tz.co.mkwanja.africa.service.UserDetailsServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Benjamini Buganzi
 * @Date 13/12/2021.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    Constants constants;

    @Autowired
    SmsService smsService;


    @PostMapping("/dash_login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        if (!loginRequest.getEmail().contains("@")) {
            throw new AccessDeniedException("Unaweza kutumia barua pepe pekee kuingia:Login only using your Email address");
        }

        if (!userRepository.existsByEmail(loginRequest.getEmail())) {
            throw new ResourceNotFoundException("Hakuna mtumiaji mwenye barua pepe " + loginRequest.getEmail() + " : No user with Email address" + loginRequest.getEmail());
        }
        User user = userRepository.findByEmail(loginRequest.getEmail()).get();


        if (user.getStatus().equals(EPerson.BLOCKED.toString()) || user.getStatus().equals(EPerson.INACTIVE.toString())) {
            throw new AccessDeniedException("Mtumiaji amefungiwa, wasiliana na System Admin kwa msaada:User is blocked, contact System Admin for support");
        }


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getPhone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getFirstname(),
                userDetails.getLastname(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles, "COMPLETED"));
    }

    @PostMapping("/app_login")
    public ResponseEntity<?> initialAppLogin(@Valid @RequestBody AppLoginRequest loginRequest) {

        if (!userRepository.existsByPhone(loginRequest.getPhone())) {
            throw new ResourceNotFoundException("Hakuna mtumiaji mwenye namba " + loginRequest.getPhone() + " : No user with Phone number " + loginRequest.getPhone());
        }

        User user = userRepository.findByPhone(loginRequest.getPhone()).get();

        if (user.getStatus() == null){
            user.setStatus(EPerson.ACTIVE.toString());
        }
        if (user.getStatus().equals(EPerson.BLOCKED.toString()) || user.getStatus().equals(EPerson.INACTIVE.toString())) {
            throw new AccessDeniedException("Mtumiaji amefungiwa, wasiliana na System Admin kwa msaada:User is blocked, contact System Admin for support");
        }

        String otp = userDetailsService.generateOTP();
        user.setPassword(encoder.encode(otp));

        System.out.println("OTP: " + otp);
        userRepository.save(user);
        //Send OTP to user through sms

        String content = "Thank you for using Hesa, your OTP is " + otp;
        smsService.sendSms(loginRequest.getPhone(), content);

        return ResponseEntity
                .ok()
                .body(new MessageResponse(200, "OTP Has been sent to user" + loginRequest.getPhone(), "OTP imetumwa kwa mtumiaji" + loginRequest.getPhone(), ""));

        //For Development purpose
//        return ResponseEntity
//                .ok()
//                .body(new MessageResponse(200, "OTP:" + otp, "OTP:" + otp));
    }

    @PostMapping("/app_user_verification")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AppUserVerification verification) {
        String nextStep = "COMPLETED";

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(verification.getPhone(), verification.getOtp()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getFirstname(),
                userDetails.getLastname(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles, "COMPLETED"));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody AppLoginRequest loginRequest) {

        if (userRepository.existsByPhone(loginRequest.getPhone())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(101, "Phone Number is already taken!", "Namba imeshatumika", ""));
        }

        // Create new user's account
        User user = new User("", "", loginRequest.getPhone(),
                null,
                null);

        user.setStatus(EPerson.INACTIVE.toString());

        String otp = userDetailsService.generateOTP();
        user.setPassword(encoder.encode(otp));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role is not found."));
        roles.add(userRole);


        user.setRoles(roles);

        userRepository.save(user);
        //Send OTP to user through sms

        String content = "Thank you for using Mkwanja, your OTP is " + otp;
        smsService.sendSms(loginRequest.getPhone(), content);

        return ResponseEntity
                .ok()
                .body(new MessageResponse(200, "OTP Has been sent to user " + loginRequest.getPhone(), "OTP imetumwa kwa mtumiaji " + loginRequest.getPhone(), ""));

    }

}
