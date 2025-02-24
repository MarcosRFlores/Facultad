﻿function c=falseposmethod(f,a,b,error)
    c= (a) - (f(a)*(b-a))/(f(b)-f(a))
    while abs(f(c))>error
        if f(c)*f(a)>0
            a=c;
        else
            b=c;
        endif
        c= (a) - (f(a)*(b-a))/(f(b)-f(a));
    endwhile


#se escribe ->@(LA VARIABLE DE LA FUNCION)
#Manera de ejecutar-> f=@(x)exp(-x)-x
#a=0;
#b=3;    
#error=0.01;
#answer=0.5695