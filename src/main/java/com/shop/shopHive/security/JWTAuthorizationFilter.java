package com.shop.shopHive.security;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

    // return type should be a string and returns fake jwt
    // FilterChain chain GIVES its ability to pass the req to the next filter on the chain

    UserDetailsServiceImpl userDetailsServiceImpl;

    private ObjectMapper objectMapper = new ObjectMapper();


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        super(authenticationManager);
        this.userDetailsServiceImpl = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return ;
        }


        try{
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (RuntimeException re){
            log.info("Runtime exception occured");

            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.FORBIDDEN, re.getMessage(),
                    re.getMessage(), Calendar.getInstance().getTime().toString());

            res.setStatus(403);

            OutputStream out = res.getOutputStream();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(out, errorDetails);
            out.flush();
            out.close();
        }

        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        log.info("Header string --> {}", token);
        if (token != null) {

            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null) {
                log.info("Returned user --> {}", user);
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
