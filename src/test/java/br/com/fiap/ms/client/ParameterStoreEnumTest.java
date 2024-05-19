package br.com.fiap.ms.client;

import br.com.fiap.ms.client.external.configuration.ParameterStoreEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterStoreEnumTest {

    @Test
    public void testGetParameterNameForRegion() {
        assertEquals("/msclient/AWS_REGION", ParameterStoreEnum.REGION.getParameterName());
    }

    @Test
    public void testGetParameterNameForDynamoEndpoint() {
        assertEquals("/msclient/AWS-DYNAMO-ENDPOINT", ParameterStoreEnum.DYNAMO_ENDPOINT.getParameterName());
    }

    @Test
    public void testGetParameterNameForAccessKeyId() {
        assertEquals("/msclient/AWS_ACCESS_KEY_ID", ParameterStoreEnum.ACCESS_KEY_ID.getParameterName());
    }

    @Test
    public void testGetParameterNameForSecretAccessKey() {
        assertEquals("/msclient/AWS_SECRET_ACCESS_KEY", ParameterStoreEnum.SECRET_ACCESS_KEY.getParameterName());
    }
}