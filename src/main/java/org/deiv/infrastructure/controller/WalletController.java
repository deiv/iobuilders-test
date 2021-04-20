package org.deiv.infrastructure.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.deiv.application.dto.DepositDto;
import org.deiv.application.dto.TransferDto;
import org.deiv.application.dto.UserDto;
import org.deiv.application.usecase.*;
import org.deiv.domain.error.InvalidDataException;
import org.deiv.domain.error.NotEnoughBalance;
import org.deiv.domain.error.WalletAlreadyExistsException;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

import static io.micronaut.http.MediaType.APPLICATION_JSON;
import static io.micronaut.http.MediaType.TEXT_PLAIN;

@Controller("/wallet")
class WalletController {

    @Inject
    private CreateWalletUseCase createWalletUseCase;

    @Inject
    private GetWalletUseCase getWalletUseCase;

    @Inject
    private MakeDepositUseCase makeDepositUseCase;

    @Inject
    private MakeTransferUseCase makeTransferUseCase;

    @Inject
    private ViewWalletMovementsUseCase viewWalletMovementsUseCase;


    @Post(processes = APPLICATION_JSON)
    HttpResponse add(@Body UserDto userInfo)
    {
        try {
            createWalletUseCase.addWallet(userInfo);

        } catch (InvalidDataException e) {
            return HttpResponse.badRequest();

        } catch (WalletAlreadyExistsException e) {
            return HttpResponse.notModified();

        }

        return HttpResponse.ok();
    }

    @Get(uri = "/{userName}", consumes = TEXT_PLAIN, produces = APPLICATION_JSON)
    HttpResponse getWallet(@NotBlank String userName) {
        try {
            return HttpResponse.ok().body(getWalletUseCase.getWallet(userName));

        } catch (InvalidDataException e) {
            return HttpResponse.badRequest().body(null);

        }
    }

    @Post(uri = "/deposit", processes = APPLICATION_JSON)
    HttpResponse add(@Body DepositDto deposit)
    {
        try {
            makeDepositUseCase.makeDeposit(deposit);

        } catch (InvalidDataException e) {
            return HttpResponse.badRequest();

        }

        return HttpResponse.ok();
    }

    @Post(uri = "/transfer", processes = APPLICATION_JSON)
    HttpResponse add(@Body TransferDto transfer)
    {
        try {
            makeTransferUseCase.makeTransfer(transfer);

        } catch (InvalidDataException e) {
            return HttpResponse.badRequest();

        } catch (NotEnoughBalance notEnoughBalance) {
            return HttpResponse.notModified();
        }

        return HttpResponse.ok();
    }

    @Get(uri = "/movements/{userName}", consumes = TEXT_PLAIN, produces = APPLICATION_JSON)
    HttpResponse getMovements(@NotBlank String userName) {
        try {
            return HttpResponse.ok().body(viewWalletMovementsUseCase.getMovements(userName));

        } catch (InvalidDataException e) {
            return HttpResponse.badRequest().body(null);
        }
    }
}
