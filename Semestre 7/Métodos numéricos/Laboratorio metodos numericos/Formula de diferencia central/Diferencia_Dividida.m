% Definir la función g(x)
g = @(x) x - sin(x);

% Valores de x0 y h
x0 = 3;  % Cambiamos x0 a 3
h = 0.25;  % Mantener h en 0.25

% Cálculo de la diferencia dividida
diferencia_dividida = (g(x0 + h) - g(x0)) / h;

% Mostrar el resultado
disp(['La diferencia dividida en x0 = 3 y h = 0.25 es: ', num2str(diferencia_dividida)]);

%+h para hacia adelante, -h para hacia atras
