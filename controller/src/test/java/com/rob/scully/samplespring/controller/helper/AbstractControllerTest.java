package com.rob.scully.samplespring.controller.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public class AbstractControllerTest {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    protected RestDocumentationResultHandler restDocumentationResultHandler;

    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected MockMvc mockMvc(Object restDocsController) {
        return mockMvc = MockMvcBuilders.standaloneSetup(restDocsController)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    protected RestDocumentationResultHandler documentPrettyPrintReqResp(String useCase) {
        return document(useCase,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));
    }


    protected void generateRestDocumentationResultHandler(final String useCase){
        restDocumentationResultHandler =  documentPrettyPrintReqResp(useCase);
        restDocumentationResultHandler.document();
    }
}
