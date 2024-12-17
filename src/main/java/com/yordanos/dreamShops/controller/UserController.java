package com.yordanos.dreamShops.controller;

import com.yordanos.dreamShops.dto.UserDto;
import com.yordanos.dreamShops.exceptions.AlreadyExistsException;
import com.yordanos.dreamShops.exceptions.ResourceNotFoundException;
import com.yordanos.dreamShops.model.User;
import com.yordanos.dreamShops.request.CreateUserRequest;
import com.yordanos.dreamShops.request.UpdateUserRequest;
import com.yordanos.dreamShops.response.ApiResponse;
import com.yordanos.dreamShops.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getUser() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Long userId = userService.getUserByUsername(username);
            User user = userService.getUserById(userId);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("Success", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request) {
        try {
            User user = userService.createUser(request);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("Create User Success!", userDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/user/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserRequest request, @RequestParam(required = false) Long userId) {
        try {
            if (userId == null) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                userId = userService.getUserByUsername(username);
            }
            User user = userService.updateUser(request, userId);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("Update User Success!", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam(required = false) Long userId) {
        try {
            if (userId == null) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                userId = userService.getUserByUsername(username);
            }
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("Delete User Success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
