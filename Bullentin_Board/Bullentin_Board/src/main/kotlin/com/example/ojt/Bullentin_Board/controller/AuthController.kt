package com.example.ojt.Bullentin_Board.controller

import com.example.ojt.Bullentin_Board.dto.LoginDto
import com.example.ojt.Bullentin_Board.dto.Message
import com.example.ojt.Bullentin_Board.entity.User
import com.example.ojt.Bullentin_Board.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = arrayOf("http://localhost:8080"))
class AuthController(private val userService: UserService, val response: HttpServletResponse) {

    @GetMapping("/hello")
    fun hello() : String {
        return "hello"
    }

    @PostMapping("/login")
    fun login(@RequestBody body: LoginDto): ResponseEntity<Any> {
        println(body.email);
        val user = this.userService.findByEmail(body.email)
            ?: return ResponseEntity.badRequest().body(Message("user not found"))

        if (!user.comparePassword(body.password)) {
            return ResponseEntity.badRequest().body(Message("Invalid Password"))
        }

        val issue = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issue)
            .setExpiration(Date(System.currentTimeMillis() + 60 *24 * 1000))
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

       val cookie = Cookie("jwt",jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }

    @GetMapping("/user")
    fun user(@CookieValue("jwt")jwt : String) : ResponseEntity<Any> {

        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }

            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body

            return ResponseEntity.ok(this.userService.getById(body.issuer.toInt()))
        }catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("unauthenticated"))
        }
    }

}
