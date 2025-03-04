# Algoritmo de Shunting Yard

Para complementar [la calculadora](https://github.com/mmasias/24-25-PRG2/blob/main/src/calculadora/README.md) que hemos hecho con los alumnos de Programación II

```
FUNCIÓN shuntingYard(expresiónInfija):
    pila = []
    salida = []
    PARA cada token EN expresiónInfija HACER:
        SI token ES un número ENTONCES:
            AÑADIR token A salida
        SI token ES un operador ENTONCES:
            MIENTRAS pila NO esté vacía Y el tope de pila sea un operador Y la precedencia del tope de pila >= precedencia de token) HACER:
                AÑADIR el tope de pila A salida
                SACAR el tope de pila
            FIN MIENTRAS
            AÑADIR token A pila
        SI token ES '(' ENTONCES:
            AÑADIR token A pila
        SI token ES ')' ENTONCES:
            MIENTRAS el tope de pila NO sea '(' HACER:
                AÑADIR el tope de pila A salida
                SACAR el tope de pila
            FIN MIENTRAS
            SACAR '(' DE pila
    FIN PARA

    // Vaciar la pila al final
    MIENTRAS pila NO esté vacía HACER:
        AÑADIR el tope de pila A salida
        SACAR el tope de pila
    FIN MIENTRAS

    DEVOLVER salida
FIN FUNCIÓN
```
