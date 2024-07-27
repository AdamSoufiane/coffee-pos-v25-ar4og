package ai.shreds.application; 
  
 import ai.shreds.shared.SharedCreateOrderRequestParams; 
 import ai.shreds.shared.SharedOrderResponseDTO; 
  
 /** 
  * Interface for creating an order within the application layer. 
  */ 
 public interface ApplicationCreateOrderInputPort { 
     /** 
      * Creates an order based on the provided request parameters. 
      * 
      * @param request the request parameters for creating the order 
      * @return the response DTO containing order details 
      */ 
     SharedOrderResponseDTO createOrder(SharedCreateOrderRequestParams request); 
 } 
 