package com.blindbox.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dancp0pcq",
                "api_key", "994869346173588",
                "api_secret", "na5tnVQw0Z4gY6PCbsizc6oEhSE"
        ));
    }
}

