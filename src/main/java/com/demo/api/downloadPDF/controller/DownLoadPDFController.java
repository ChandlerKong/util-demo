package com.demo.api.downloadPDF.controller;

import com.demo.api.downloadPDF.service.DownLoadPDFService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/downloadPDF")
public class DownLoadPDFController {

    private final DownLoadPDFService downLoadPDFService;


    @PostMapping()
    public void download(HttpServletResponse response) throws IOException {
        downLoadPDFService.download(response);
    }
}
