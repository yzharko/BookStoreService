package ru.goth.controller;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class JsonConvertor<T> {
    private static final Logger logger = Logger.getLogger(JsonConvertor.class.getName());
    private HttpServletResponse response;
    private final Gson gson = new Gson();

    public JsonConvertor( HttpServletResponse response) {
        this.response = response;
    }

    public void convertToJson(T dto) {
        String json = gson.toJson(dto);

        try (PrintWriter printWriter = response.getWriter()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            printWriter.write(json);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }
}
