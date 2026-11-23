package org.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PokeAPIException extends RuntimeException {
    public PokeAPIException(String pack, String message) {
        Logger log = LoggerFactory.getLogger(pack);
        super(message);
    }
}
