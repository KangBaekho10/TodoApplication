package org.todoapplication.todoapplication.domain.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/token")
@RestController
class JwtTestingController {

    @GetMapping
    fun getJwtToken(@RequestParam("userName") userName : String) : String{
        val jwtPlugin = JwtPlugin()
        return jwtPlugin.generateAccessToken("username",userName)
    }
    @GetMapping("/valid")
    fun validJwtToken(@RequestParam("token") token : String) : Result<Jws<Claims>>{
        val jwtPlugin = JwtPlugin()
        val validateToken = jwtPlugin.validateToken(token)
        return validateToken
    }

    @PostMapping
    fun login(@RequestParam("userEmail") userEmail : String) :String{
        // TODO("loginservice 에서 DB에 해당 email을 가진 회원이 있는가? pw도 일치 하는가? 등을 확인")

        val jwtPlugin = JwtPlugin()
        return jwtPlugin.generateAccessToken("userEmail",userEmail)
    }

}