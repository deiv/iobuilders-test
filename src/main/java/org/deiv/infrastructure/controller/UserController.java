package org.deiv.infrastructure.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import org.deiv.application.usecase.RegisterUserUseCase;
import org.deiv.application.dto.UserDto;
import org.deiv.domain.error.AccountAlreadyExistsException;
import org.deiv.domain.error.InvalidDataException;

import javax.inject.Inject;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("/user")
class UserController {

    @Inject
    private RegisterUserUseCase registerUserUseCase;

    @Post(processes = APPLICATION_JSON)
    HttpResponse add(@Body UserDto addAccountDto)
    {
        try {
            registerUserUseCase.registerUser(addAccountDto);

        } catch (InvalidDataException e) {
            return HttpResponse.badRequest();

        } catch (AccountAlreadyExistsException e) {
            return HttpResponse.notModified();
        }

        return HttpResponse.ok();
    }
}
