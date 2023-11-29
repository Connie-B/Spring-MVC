 package com.example.quoters;

 import java.util.Objects;
 
 import jakarta.persistence.Entity;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.Id;
 
 @Entity
 class Quote {
 
     @Id @GeneratedValue private Long id;
     private String quote;
 
     Quote(String quote) {
         this.quote = quote;
     }
 
     protected Quote() {}
 
     public Long getId() {
         return id;
     }
 
     public void setId(Long id) {
         this.id = id;
     }
 
     public String getQuote() {
         return quote;
     }
 
     public void setQuote(String quote) {
         this.quote = quote;
     }
 
     @Override
     public boolean equals(Object o) {
         if (this == o)
             return true;
         if (!(o instanceof Quote))
             return false;
         Quote quote1 = (Quote) o;
         return Objects.equals(id, quote1.id) && Objects.equals(quote, quote1.quote);
     }
 
     @Override
     public int hashCode() {
         return Objects.hash(id, quote);
     }
 
     @Override
     public String toString() {
         return "Quote{" + "id=" + id + ", quote='" + quote + '\'' + '}';
     }
 }