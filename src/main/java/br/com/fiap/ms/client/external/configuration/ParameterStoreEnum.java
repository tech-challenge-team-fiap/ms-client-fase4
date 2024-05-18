package br.com.fiap.ms.client.external.configuration;

public enum ParameterStoreEnum {

    REGION("/msclient/AWS_REGION"),
    DYNAMO_ENDPOINT("/msclient/AWS-DYNAMO-ENDPOINT"),
    ACCESS_KEY_ID("/msclient/AWS_ACCESS_KEY_ID"),
    SECRET_ACCESS_KEY("/msclient/AWS_SECRET_ACCESS_KEY");

    private final String parameterName;

    ParameterStoreEnum(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }

}
