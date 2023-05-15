package com.shop.shopHive.security.exceptions;
@Slf4j
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     */

    private final JsonObject jsonObjectImpl = new JsonObject();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("Authentication handler returning response");

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, exception.getMessage(),
                exception.getMessage(), Calendar.getInstance().getTime().toString());


        OutputStream out = response.getOutputStream();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(out, errorDetails);
        out.flush();
        out.close();
    }
    
}
