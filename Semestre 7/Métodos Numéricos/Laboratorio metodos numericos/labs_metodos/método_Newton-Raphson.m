% Definir la función f(x)
function y = my_script9(x)
    y = x^2 - 2;  % Ejemplo: f(x) = x^2 - 2 (queremos hallar √2)
end

% Definir la derivada f'(x)
function y = df(x)
    y = 2 * x;  % Derivada de f(x) = x^2 - 2 es f'(x) = 2x
end

% Parámetros iniciales
x0 = 1;  % Aproximación inicial
tol = 1e-5;  % Tolerancia deseada
max_iter = 100;  % Máximo número de iteraciones

% Inicializar el valor actual x_n y el contador de iteraciones
x_n = x0;
iter = 0;

% Método de Newton-Raphson
while abs(f(x_n)) > tol && iter < max_iter
    iter = iter + 1;  % Incrementar el contador de iteraciones
    x_n = x_n - f(x_n) / df(x_n);  % Actualizar x_n con la fórmula de Newton
endwhile

% Imprimir el resultado
printf("La raíz aproximada es: %.6f\n", x_n);
printf("Se encontró en %d iteraciones\n", iter);
