function c=bisectmethod(f,a,b,error)
    c=(a+b)/2;
    while abs(f(c))>error
        if f(c)*f(a)>0
            a=c;
        else
            b=c;
        endif
        c=(a+b)/2;
    endwhile


#Manera de ejecutar-> f=@(x)x^2-2x-5
#f=@(x)('x^2-2x-5');
#a=2;
#b=3;    
#error=0.01;