package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;

import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.utils.mappers.UserMapper;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.contracts.Authentication.UserResponse;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.model.User;
import ar.edu.unq.desapp.grupog.backendapicryptoexchange.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Tag(name = "Users", description = "Users APIs")

public class UserController {

    @Autowired
    IUserService userService;

    @Operation(
            summary = "Retrieve all users",
            description = "Get all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully", content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "User not found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new UserMapper().mapToUserResponses(users));
    }
}
