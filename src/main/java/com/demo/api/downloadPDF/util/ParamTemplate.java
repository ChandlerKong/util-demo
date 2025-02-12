package com.demo.api.downloadPDF.util;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ParamTemplate {
    /**
     * 模板文件路径
     */
    private String templatePath;
    /**
     * 字体文件路径 不传默认为宋体
     */
    private List<String> fontsPath;
    /**
     * 参数列表
     */
    private Map<String,String> params;
    /**
     * 列表参数合集
     */
    private Map<String,List<Object>> listParams;
    /**
     * 图片
     */
    private Map<String,ImageTemplate> imageTemplates;
    /**
     * 输出文件类型 默认pdf 支持返回word .docx
     */
    private String resultType = ".pdf";

    public ParamTemplate(String templatePath,
                         List<String> fontsPath,
                         Map<String, String> params,
                         Map<String, List<Object>> listParams,
                         Map<String,ImageTemplate> imageTemplates) {
        this.templatePath = templatePath;
        this.fontsPath = fontsPath;
        this.params = params;
        this.listParams = listParams;
        this.imageTemplates = imageTemplates;
    }

    public ParamTemplate(String templatePath, Map<String, String> params) {
        this.templatePath = templatePath;
        this.params = params;
    }
}
