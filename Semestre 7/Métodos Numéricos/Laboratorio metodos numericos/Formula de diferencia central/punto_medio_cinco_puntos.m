% Definir la función g(x)
g = @(x) x - sin(x);

% Valores de x0 y h
x0 = 3;  % Cambiamos x0 a 3
h = 0.25;  % Mantener h en 0.25

% Cálculo de la del punto medio de cinco puntos
punto_medio_cinco_puntos = (g(x0 -(2 * h)) - (8 * g(x0 - h)) + (8 * g(x0+h) - g(x0 - (2 * h)) / (12 * h);

% Mostrar el resultado
punto_medio_cinco_puntos = (g(x0 - (2 * h)) - 8 * g(x0 - h) + 8 * g(x0 + h) - g(x0 + (2 * h))) / (12 * h);
%Formula del punto medio de cinco puntos
