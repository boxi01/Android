package quotation;

import java.util.List;

public class QHolder {

        List<Quotation> quotations;
        public QHolder(List<Quotation> quotations) {
            this.quotations = quotations;
        }
        public void setQuotations(List<Quotation> quotations) {
            this.quotations = quotations;
        }
        public List<Quotation> getQuotations() {
            return this.quotations;
        }

}
