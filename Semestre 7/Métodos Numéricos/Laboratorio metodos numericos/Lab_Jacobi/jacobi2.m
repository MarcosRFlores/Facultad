A = [5 1 0; 0 4 -3; 2 1 5];  % Matriz de coeficientes del sistema Ax = b
b = [6 0 3]';  % Vector de términos independientes (transpuesto para que sea columna)
x = [0 0 0]';  % Vector inicial de las soluciones (inicializado en ceros)
n = size(x, 1);  % Tamaño del vector x (número de incógnitas, en este caso 3)
normVal = inf;  % Se inicializa la norma como infinito para entrar en el bucle
tol = 1e-1;  % Tolerancia, criterio de convergencia; el bucle parará cuando la diferencia entre iteraciones sea menor a este valor
itr = 0;  % Contador de iteraciones, empieza en 0

while normVal > tol  % El bucle continúa mientras la norma (diferencia entre iteraciones) sea mayor que la tolerancia
    xold = x;  % Se guarda el valor anterior de x para calcular el cambio (norma) entre iteraciones

    for i = 1:n  % Bucle para recorrer las ecuaciones (filas de la matriz A)
        sigma = 0;  % Inicializa sigma en 0; sigma es la suma de los términos no diagonales de la ecuación
        for j = 1:n  % Bucle para recorrer los términos de cada ecuación
            if j ~= i  % Solo considera los elementos fuera de la diagonal (excluye A(i, i))
                sigma = sigma + A(i, j) * x(j);  % Calcula el término sigma, sumando los productos A(i,j) * x(j)
            end
        end
        x(i) = (1 / A(i, i)) * (b(i) - sigma);  % Actualiza la solución para x(i), usando la fórmula del método de Jacobi:
                                                % x(i) = (b(i) - sigma) / A(i, i)
    end

    itr = itr + 1;  % Incrementa el contador de iteraciones
    normVal = norm(xold - x, 1);  % Calcula la norma de la diferencia entre el nuevo x y el anterior (xold).
                                  % Aquí se usa la norma 1 (suma de valores absolutos).
end

fprintf("Solución del sistema: \n%f\n%f\n%f en %d iteraciones", x, itr);  
% Imprime los valores de x y el número de iteraciones
