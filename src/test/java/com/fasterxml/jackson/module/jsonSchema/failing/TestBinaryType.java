package com.fasterxml.jackson.module.jsonSchema.failing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.fasterxml.jackson.module.jsonSchema.SchemaTestBase;
import com.fasterxml.jackson.module.jsonSchema.factories.WrapperFactory.JsonSchemaVersion;

public class TestBinaryType extends SchemaTestBase
{
    private final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Test simple generation for simple/primitive numeric types
     */
    public void testBinaryType() throws Exception
    {
        JsonSchemaGenerator generator = new JsonSchemaGenerator(MAPPER, JsonSchemaVersion.DRAFT_V4);
        JsonSchema schema;

        schema = generator.generateSchema(byte[].class);

        // Should be either an array of bytes, or, String with 'format' of "base64"
        String json = MAPPER.writeValueAsString(schema);

        if (!json.equals(aposToQuotes("{'type':'array','items':{'type':'byte'}}"))) {
            String pretty = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
            fail("Should get 'array of bytes' or 'String as Base64', instead got: "+pretty);
        }
    }
}
