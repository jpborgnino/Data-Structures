public class NBody{
 public double readRadius(String filename)
{
In in = new In(filename);
int N = in.readInt();
double R = in.readDouble();
return R;
}
}
