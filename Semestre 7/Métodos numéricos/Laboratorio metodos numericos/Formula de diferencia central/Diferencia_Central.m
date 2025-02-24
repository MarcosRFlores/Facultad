% Definir la función g(x)
g = @(x) x - sin(x);

% Valores de x0 y h
x0 = 3;  % Cambiamos x0 a 3
h = 0.25;  % Mantener h en 0.25

% Cálculo de la diferencia central
diferencia_central = (g(x0 + h) - g(x0 - h)) / (2 * h);

% Mostrar el resultado
disp(['La diferencia central en x0 = 3 y h = 0.25 es: ', num2str(diferencia_central)]);
FORMULA DE DIFERENCIA CENTRAL
