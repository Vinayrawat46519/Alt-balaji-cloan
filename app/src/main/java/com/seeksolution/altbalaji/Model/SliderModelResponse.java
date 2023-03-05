package com.seeksolution.altbalaji.Model;

import java.util.ArrayList;

public class SliderModelResponse {
        public String code;
        public String message;
        public ArrayList<SliderModel> data;
        public boolean error;
        public boolean status;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<SliderModel> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }

    public boolean isStatus() {
        return status;
    }

    public SliderModelResponse(String code, String message, ArrayList<SliderModel> data, boolean error, boolean status) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.error = error;
        this.status = status;
    }
}

