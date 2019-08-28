import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Fiscalia } from './fiscalia';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FiscaliasService {

  constructor(private http: HttpClient) { }

  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  // POST
  CreateFiscalia(data): Observable<boolean> {
    return this.http.post<boolean>('http://localhost:4567/fiscalias', JSON.stringify(data), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    );
  }

  // GET
  GetFiscalias(): Observable<Fiscalia[]> {
    return this.http.get<Fiscalia[]>('http://localhost:4567/fiscalias')
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    );
  }

  // DELETE
  DeleteFiscalia(id): Observable<boolean>{
    return this.http.delete<boolean>(`http://localhost:4567/fiscalias/${id}`, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  // Error handling
  errorHandl(error) {
     let errorMessage = '';
     if(error.error instanceof ErrorEvent) {
       // Get client-side error
       errorMessage = error.error.message;
     } else {
       // Get server-side error
       errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
     }
     console.log(errorMessage);
     return throwError(errorMessage);
  }

}

