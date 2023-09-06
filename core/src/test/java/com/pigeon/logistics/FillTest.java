package com.pigeon.logistics;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.MapUtils;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @see <a href="https://github.com/alibaba/easyexcel/edit/master/easyexcel-test/src/test/java/com/alibaba/easyexcel/test/demo/fill/FillTest.java">easyexcel例子</a>
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FillTest {

    @Test
    @Order(1)
    @DisplayName("最简单的Excel填充")
    public void simpleFill() {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        String templateFileName = "D:\\IdeaProjects\\pigeon-logistics\\core\\src\\test\\java\\com\\pigeon\\logistics\\simple.xlsx";

        // 方案1 根据对象填充
        String fileName1 =
                "D:\\IdeaProjects\\pigeon-logistics\\core\\target\\classes\\temp\\simple-"
                        + System.currentTimeMillis() + "1.xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        FillData fillData = new FillData();
        fillData.setName("张三");
        fillData.setNumber(5.2);
        EasyExcel.write(fileName1).withTemplate(templateFileName).sheet().doFill(fillData);

        // 方案2 根据Map填充
        String fileName2 =
                "D:\\IdeaProjects\\pigeon-logistics\\core\\target\\classes\\temp\\simple-"
                        + System.currentTimeMillis() + "2.xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        Map<String, Object> map = MapUtils.newHashMap();
        map.put("name", "李四");
        map.put("number", 5.3);
        EasyExcel.write(fileName2).withTemplate(templateFileName).sheet().doFill(map);

    }


}
