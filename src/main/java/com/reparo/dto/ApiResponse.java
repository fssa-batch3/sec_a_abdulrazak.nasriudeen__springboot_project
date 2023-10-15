package com.reparo.dto;



public class ApiResponse {
   private int statusCode;
   private  String message;

   private String data;
   private  String error;

 public static   final String SUCCESS = "success";
   public static final String FAILED = "failed";
   public static   final int SUCCESS_CODE = 200;
   public static final int FAIL_CODE = 400;

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
