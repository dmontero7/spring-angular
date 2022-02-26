package com.damb.springboot.backend.apirest.auth;

public class JwtConfig {
    public static final String LLAVE_SECRETA = "alguna.llave.secreta.importante";
    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n"
	    + "MIIEowIBAAKCAQEAoR/eXeP+mojXaLc/R3wOdBareLn8OVUtWNc1Bl/sx71viux+\r\n"
	    + "hFz56NI4qGp8Yyc7stj1/LmowDZ6jCnV5pl1JAGseyQfDZ8xWF/4Wvvsn+6NxKMl\r\n"
	    + "00YbJ+Gg/9krRvcY2wjCDDCJvRhuuXAQq6o6qZ1sFbuFKSNLPs5fjVH5/ofbSz61\r\n"
	    + "ZCB3faCGRsnIW5Tuvw4d704bmM8ZfPpdwiZvP+XzIC8AutQCCgWR7Sus1YR08i+D\r\n"
	    + "rtXT+QrvglQoBvhDmSpvNP8HxRPJQGYD89zVRzBWmr9gdtvnPcKAO2+NAIeW1ChP\r\n"
	    + "GrDp032G674SgTeo5W9SpDdlD3UkaYkUcdBXRwIDAQABAoIBAHucvnWb4A3g5Bnz\r\n"
	    + "VDnchB4t8jl5vnah0VtyJ70PRNHslLGpRFr6FcS32z3iDDHpvJQ2fWn5aeylHxl/\r\n"
	    + "UxpKKyOHoTMMAELsGehqIFh1JBGC7AKrA+71vwm2wVTn4JreIZSRQIYnitSm9POR\r\n"
	    + "dUiuHblZC74UklGAQU8a9xWtibTqf8LMTXf0kHJMNGNlDuwQFQ6/Y4WWMB0QeLsX\r\n"
	    + "sjhdGgwGEh6ge9IKPgq67Uxojay3p8Kk7S3qKEl0gSnRwZPuHPaqYxFM2H/G5dZc\r\n"
	    + "9ID5QXpLAVDFtxmBPlNXb0Rd4p/DRBsjE2vU1EVlqXODTEs1e2AaBuNt/9iE91b5\r\n"
	    + "CDF9khECgYEA029NEl9XVuUcbldq2leCp9hdgt4k5XRQv7fu+G7CX1vV05YBns63\r\n"
	    + "qujANl4zUZJZm/uQiuBj+XkKmtmBcuSG7LZAYw7ZDLUgA2FjF+WYs4xHK9igvEGd\r\n"
	    + "kEKwErW/lHkfctO10Qx45UZI6ANwzvXaQ+WpbQSjKJSFh5WLi6BMA4kCgYEAwxXm\r\n"
	    + "sEK26Fbpovu4gnH+Yi152CVXJjrkwPVDyJdPn1H+sFnmngk5XfkjoX1f1mf6Vmc/\r\n"
	    + "n2OFCFI5UAeRWZL36m0t/bDEbpZZCVK77kVbvTl1tQGYrBVjh5O1d7OJT3g4GicO\r\n"
	    + "SsyKiBOMJqiSWPZN+rRB9B3ll+WlfryzFZpxQE8CgYAQDbNcYcDP6DVwa1LBoUNa\r\n"
	    + "WyM+fH9wrJQ0+lI6qq43b1Gc7xbQaAGqCGIPvrdGykXjggZHhSISHw6fE0Sw5Ika\r\n"
	    + "D/xziP0R6e60UwCjH9uoRk5hFeGeaZz9BxM3WHVjYI7yuQBlDv1e6yc99oTqMeCQ\r\n"
	    + "py9mB6QbwKsi0Ef4IgOjgQKBgEF7YnmbiyWltrqcX6x5cvelaBajQgN1FT+nk98K\r\n"
	    + "2xERRroPExfM5NKkQ8968+ZV9HN4KKTplMfcQZyZTbKRYGHxz9Qtt2GfWbeFa8to\r\n"
	    + "wIhddyMQrBxm+YeSjwDk5JwmMD6nCWcWDRHr7ck0IBt4YWhbhR/ZwS0hSlGmKT6B\r\n"
	    + "Hq6FAoGBAMX/tzfh3ssnEY8oItj3OkabsjUCfsetqUllOTNs55XhtCnNb9EjxDhN\r\n"
	    + "ljHZ9u4OxwInsxFCvDXzL+q2MGZpxSBnslrRSFVDTcJH5YjxCKjmB9OUl71YxeJl\r\n"
	    + "mSIwwbTv0ndGcKzwYmZjqhdHbdeqsfe1Esjq6s/O2FhIo1kVQR27\r\n" + "-----END RSA PRIVATE KEY-----";
    public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n"
	    + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoR/eXeP+mojXaLc/R3wO\r\n"
	    + "dBareLn8OVUtWNc1Bl/sx71viux+hFz56NI4qGp8Yyc7stj1/LmowDZ6jCnV5pl1\r\n"
	    + "JAGseyQfDZ8xWF/4Wvvsn+6NxKMl00YbJ+Gg/9krRvcY2wjCDDCJvRhuuXAQq6o6\r\n"
	    + "qZ1sFbuFKSNLPs5fjVH5/ofbSz61ZCB3faCGRsnIW5Tuvw4d704bmM8ZfPpdwiZv\r\n"
	    + "P+XzIC8AutQCCgWR7Sus1YR08i+DrtXT+QrvglQoBvhDmSpvNP8HxRPJQGYD89zV\r\n"
	    + "RzBWmr9gdtvnPcKAO2+NAIeW1ChPGrDp032G674SgTeo5W9SpDdlD3UkaYkUcdBX\r\n" + "RwIDAQAB\r\n"
	    + "-----END PUBLIC KEY-----";
}
