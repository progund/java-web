package org.henrikard.student.formats;
import java.util.List;
import org.henrikard.student.domain.Student;

public interface Formatter{
    public void   loadFromList(List<Student> list);
    public String getDocument();
    public String getContentType();
}
