;-----Identificador
[id.nome] ;valor armazenado dentro de id.nome
;-----Numero
[numero.getValue()] ;valor armazendado em numero.getValue()
;-----EstadoLampada

;-----EstadoRobo
    ;pronto:
        IN 11, AL
        AND AL, 00000010b
        CMP AL, 0
        JE pronto_true
        MOV BL, 1; padrão para falso
        jmp pronto_end
        pronto_true:
        MOV BL, 0; padrão para verdadeiro        
        pronto_end:
    ;ocupado:
        IN 11, AL
        AND AL, 00000010b
        CMP AL, 0
        JNE estado_true
        MOV BL, 1; padrão para falso
        JMP ocupado_end
        estado_true:
        MOV BL, 0; padrão para verdadeiro
        ocupado_end:
    ;parado:
        ;if (!movimentando) then return true
        IN 9, AL
        CMP AL, 1;ultima instrução foi movimenta para frente
        JNE parado_true
        IN 11, AL
        AND AL, 00000010b
        CMP AL, 0;testa se  robo está pronto
        JE parado_true
        MOV BL, 1;false
        JMP parado_fim
        parado_true:
        MOV BL, 0;true
        parado_fim:
    ;movimentando:
        ;if (ultima instrucao == mova && não está pronto ainda) return true
        IN 9, AL;ultima instrucao
        CMP AL, 1; == MOVA
        JNE else1
        ;testa se não está pronto ainda:
        IN 11, AL
        AND AL, 00000010b
        CMP AL, 0
        JE else1
        ;return true
        MOV BL, 0
        JMP movimentando_fim
        else1:
        MOV BL, 1; false
        movimentando_fim:
    
;-----Condicao
    ;robo EstadoRobo
        getAsm(estadoRobo);função q retorna string com programa em asm
    ; frente robo bloqueada
        MOV AL, 4
        OUT 9, AL;examina
        IN 10, AL;resultado do exame
        CMP AL, 00000000b
        JE frente_robo_true
        MOV BL, 1;false
        JMP frente_robo_fim
        frente_robo_true:
        MOV BL, 0;true
        frente_robo_fim:
    ;direita robo bloqueada
        MOV AL, 4
        OUT 9, AL; examina
        IN 10, AL; resultado do exame
        CMP AL, 11
        JE direita_robo_true
        CMP AL, 12
        JE direita_robo_true
        CMP AL, 15
        JE direita_robo_true
        MOV BL, 1;false
        JMP direita_robo_fim
        direita_robo_true:
        MOV BL, 0;true
        direita_robo_fim:
    ;esquerda robo bloqueada
        MOV AL, 4
        OUT 9, AL; examina
        IN 10, AL; resultado do exame
        CMP AL, 9
        JE esquerda_robo_true
        CMP AL, 10
        JE esquerda_robo_true
        CMP AL, 240
        JE esquerda_robo_true
        MOV BL, 1;false
        JMP esquerda_robo_fim
        esquerda_robo_true:
        MOV BL, 0;true
        esquerda_robo_fim:
    ;lampada acesa a frente
        MOV AL, 4
        OUT 9, AL
        IN 10, AL
        CMP AL, 7
        JE lampada_acesa_frente_true
        MOV BL, 1; false
        JMP lampada_acesa_frente_fim
        lampada_acesa_frente_true:
        MOV BL, 0; true
        lampada_acesa_frente_fim:
    ;lampada apagada a frente
        MOV AL, 4
        OUT 9, AL
        IN 10, AL
        CMP AL, 8
        JE lampada_apagada_frente_true
        MOV BL, 1; false
        JMP lampada_apagada_frente_fim
        lampada_apagada_frente_true:
        MOV BL, 0; true
        lampada_apagada_frente_fim:
    ;lampada acesa a esquerda
        MOV AL, 4
        OUT 9, AL
        IN 10, AL
        CMP AL, 9
        JE lampada_acesa_esquerda_true
        MOV BL, 1; false
        JMP lampada_acesa_esquerda_fim
        lampada_acesa_esquerda_true:
        MOV BL, 0; true
        lampada_acesa_esquerda_fim:
    ;lampada apagada a esquerda
        MOV AL, 4
        OUT 9, AL
        IN 10, AL
        CMP AL, 10
        JE lampada_apagada_esquerda_true
        MOV BL, 1; false
        JMP lampada_apagada_esquerda_fim
        lampada_pagada_esquerda_true:
        MOV BL, 0; true
        lampada_apagada_esquerda_fim:
    ;lampada acesa a direita
        MOV AL, 4
        OUT 9, AL
        IN 10, AL
        CMP AL, 11
        JE lampada_acesa_direita_true
        MOV BL, 1; false
        JMP lampada_acesa_direita_fim
        lampada_acesa_direita_true:
        MOV BL, 0; true
        lampada_acesa_direita_fim:
    ;lampada apagada a direita
        MOV AL, 4
        OUT 9, AL
        IN 10, AL
        CMP AL, 12
        JE lampada_apagada_direita_true
        MOV BL, 1; false
        JMP lampada_apagada_direita_fim
        lampada_apagada_direita_true:
        MOV BL, 0; true
        lampada_apagada_direita_fim: