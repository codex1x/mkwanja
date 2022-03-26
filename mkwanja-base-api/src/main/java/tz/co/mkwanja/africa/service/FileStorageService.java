package tz.co.mkwanja.africa.service;

import tz.co.mkwanja.africa.config.file.FileStorageProperties;
import tz.co.mkwanja.africa.exceptions.FileStorageException;
import tz.co.mkwanja.africa.exceptions.MyFileNotFoundException;
import tz.co.mkwanja.africa.helpers.Constants;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    Constants constants;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException();
        }
    }

    public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException();
        }
    }

    public String storeFile(String imageString, String filename) {

        String separator = ",";
        String encodedFile;
        String extension = "";
        if (imageString.contains(separator)) {
            String[] parts = imageString.split(separator);
             encodedFile = parts[1];
            String part = parts[0];
            if (part.contains("image")) {
                 extension = part.substring(part.indexOf("/") + 1, part.indexOf(";"));
            }else {
                extension = "jpg";
            }
        }else {
            encodedFile = imageString;
        }
        filename = filename+"."+extension;

        byte[] decodedBytes = Base64.getDecoder().decode(encodedFile);

        Path targetLocation = this.fileStorageLocation.resolve(filename);


        try {
            File file = new File(targetLocation.toUri());
            FileUtils.writeByteArrayToFile(file, decodedBytes);

            return filename;
        } catch (IOException ex) {
            throw new FileStorageException();
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException(fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException(fileName);
        }
    }

    public String saveImage(MultipartFile file) {
        String name = storeFile(file);

        // String fileDownloadUrithumb = ServletUriComponentsBuilder.fromCurrentContextPath()
        String fileDownloadUrithumb = new StringBuilder(constants.appUrl)
                .append("/downloadFile/")
                .append(name)
                .toString();

        return fileDownloadUrithumb;
    }

    public String saveImage(String imageStringByte, String filename) {
        String name = storeFile(imageStringByte, filename);

        // String fileDownloadUrithumb = ServletUriComponentsBuilder.fromCurrentContextPath()
        String fileDownloadUrithumb = new StringBuilder(constants.appUrl)
                .append("/downloadFile/")
                .append(name)
                .toString();

        return fileDownloadUrithumb;
    }

    public void removePreviousImageIFExists(String pictureUrl) {
        if (pictureUrl != null) {
            String filename = pictureUrl.substring(pictureUrl.lastIndexOf("/") + 1);
            deleteFile(filename);
        }
    }

    public boolean deleteFile(String filename) {
        Path filePath = this.fileStorageLocation.resolve(filename).normalize();
        File file = new File(filePath.toUri());
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

}