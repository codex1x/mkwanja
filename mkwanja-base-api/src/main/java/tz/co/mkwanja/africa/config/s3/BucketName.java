package tz.co.mkwanja.africa.config.s3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {

    UZA_IMAGE("mkwanja");
    private final String bucketName;
}
