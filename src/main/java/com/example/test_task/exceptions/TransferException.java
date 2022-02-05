package com.example.test_task.exceptions;

import com.example.test_task.dto.AccRequestDTO;
import com.example.test_task.dto.AccRequestForTransferDTO;

public class TransferException extends Exception {

    private AccRequestDTO accRequestDTO;
    private AccRequestForTransferDTO accRequestForTransferDTO;

    public TransferException(AccRequestDTO accRequestDTO) {
        this.accRequestDTO = accRequestDTO;
    }
    public TransferException(AccRequestForTransferDTO accRequestForTransferDTO) {
        this.accRequestForTransferDTO = accRequestForTransferDTO;
    }
}
