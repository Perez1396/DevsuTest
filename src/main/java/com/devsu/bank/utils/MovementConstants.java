package com.devsu.bank.utils;

public class MovementConstants {
    public static final String BASE_MOVEMENT_PATH = "movimientos";
    public static final String PATH_MOVEMENT_ID = "/{movementId}";
    public static final String MAX_AMOUNT_REACHED = "Cupo diario excedido";
    public static final String WITHDRAWAL_EXCEDEED = "El valor que intenta retirar es mayor de lo que tiene en la cuenta";
    public static final String NO_BALANCE = "Saldo no disponible ";
    public static final String WITHDRAWAL_ERROR_LOG = "El valor a retirar fue: {} y es mayor al saldo actual de la cuenta: {}";
    public static final String NO_BALANCE_ERROR_LOG = "El valor a retirar fue: {} y el saldo en la cuenta es de 0";
}
