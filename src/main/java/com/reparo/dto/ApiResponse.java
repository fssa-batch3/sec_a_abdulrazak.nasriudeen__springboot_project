package com.reparo.dto;


import org.json.JSONObject;

public class ApiResponse {
   private int statusCode;
   private  String message;

   private String data;
   private  String error;

 public static   final String success = "success";
   public static final String failed = "failed";
   public static   final int successCode = 200;
   public static final int failCode = 400;

   public ApiResponse(int statusCode, String message) {

      this.statusCode = statusCode;
      this.message = message;

   }
   public ApiResponse(int statusCode, String message , String error ) {
      this.statusCode = statusCode;
      this.message = message;
      this.error = error;
   }

   public String getData() {
      return data;
   }

   public void setData(String  data) {
      this.data = data;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }

   public String getMessage() {
      return message;
   }

   public int getStatusCode() {
      return statusCode;
   }

   public void setStatusCode(int statusCode) {
      this.statusCode = statusCode;
   }

   public void setMessage(String message) {
      this.message = message;
   }

}
