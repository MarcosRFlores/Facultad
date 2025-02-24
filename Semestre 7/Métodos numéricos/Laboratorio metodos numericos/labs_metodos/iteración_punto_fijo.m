function c=my_scrip5(f,g,a,error)
  c=g(a);
  while abs(f(c))>error
    a=c;
    c=g(a)
  endwhile
end