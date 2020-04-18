package online.pandm.demo.annotation.testTwo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataCheckRule implements ConstraintValidator<DataCheck, String> {

   private DataCheck dataCheck;

   public void initialize(DataCheck constraint) {
      this.dataCheck = constraint;
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      SimpleDateFormat sdf = new SimpleDateFormat(dataCheck.pattern());
      try {
         Date data = sdf.parse(obj);
         return true;
      } catch (Exception e){
         return false;
      }
   }
}
