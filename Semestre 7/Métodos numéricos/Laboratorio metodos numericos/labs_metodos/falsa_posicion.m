function c=my_scrip4(f,a,b,error)
  c=a-f(a)*(b-a)/(f(b)-f(a));
  while abs(f(c))>error
    a=b;
    b=c;
    c=a-f(a)*(b-a)/(f(b)-f(a));
  endwhile
end