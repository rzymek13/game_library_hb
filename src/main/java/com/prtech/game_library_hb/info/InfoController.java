package com.prtech.game_library_hb.info;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private String url;
    
    private String myProp;

    @GetMapping("/info/url")
    String url() {
        return url;
    }
    @GetMapping("/info/prop")
    String prop() {
        return myProp;
    }
}
