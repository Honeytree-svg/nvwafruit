package bean;

import java.util.ArrayList;
import java.util.List;

public class PageCount {
    private int noteAllCount=-1;
    private int pageCount=-1;
    private int pageNoteCount=-1;
    private List allNote=null;
    public PageCount(List an,int pnc){
        allNote=an;
        noteAllCount=allNote.size();
        pageNoteCount=pnc;
        pageCount=(noteAllCount+pageNoteCount-1)/pageNoteCount;
    }
    public int PageCount(){
        return pageCount;
    }

    public List getPageList(int tp){
        List list=new ArrayList();
        if (tp>pageCount)
            tp=pageCount;
        if (tp<0)
            tp=1;
        int i=(tp-1)*pageNoteCount;
        if(i<0)i=0;
        for (int j=0;j<pageNoteCount;j++){
            if(i<noteAllCount)list.add(allNote.get(i));
            else break;
            i++;
        }
        return list;
    }
   /* public static void main(String []s){
        List list=new ArrayList();
        PageCount pageCount=new PageCount(list,8);
        System.out.println(pageCount.noteAllCount);
        System.out.println(pageCount.pageCount);
        System.out.println(pageCount.pageNoteCount);
        list=pageCount.getPageList(1);

        System.out.println(list.size());
    }*/
}
