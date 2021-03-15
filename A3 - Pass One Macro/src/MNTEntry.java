public class MNTEntry {
    
    String name;
    int pp;
    int kp;
    int mdtp;
    int kpdtp;

    MNTEntry(String name,int mdtp, int kpdtp)
    {
        this.name = name;
        this.pp = 0;
        this.kp = 0;
        this.mdtp = mdtp;
        this.kpdtp = kpdtp;
    }

    MNTEntry(String name,int pp,int kp,int mdtp, int kpdtp)
    {
        this.name = name;
        this.pp = pp;
        this.kp = kp;
        this.mdtp = mdtp;
        this.kpdtp = kpdtp;
    }

    public void incrementPP()
    {
        pp++;
    }

    public void incrementKP()
    {
        kp++;
    }

    @Override
    public String toString()
    {
        String ans = name + " " + pp + " " + kp + " " + mdtp + " " + kpdtp;

        return ans;
    }

}
