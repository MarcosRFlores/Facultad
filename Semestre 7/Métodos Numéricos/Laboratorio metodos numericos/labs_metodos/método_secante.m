function c=my_script2(f,a,b,error)
     c = a-(f(a)*(b-a))/(f(b)-f(a));
     while abs(f(c))>error
        if f(c)*f(a)>0
            a=c;
        else
            b=c;
        endif
        c=a-(f(a)*(b-a))/(f(b)-f(a));
     endwhile
endfunction
