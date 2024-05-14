package org.todoapplication.todoapplication.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info

class SwaggerConfig {
    fun openAPI(): OpenAPI = OpenAPI()
    .components(Components())
    .info(
        Info()
            .title("TodoApplication API")
            .description("TodoApplication API schema")
            .version("1.0")
    )
}