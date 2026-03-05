import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class EquipamentoService {
  private readonly API = 'http://localhost:8080/api/equipamento';

  constructor(private http: HttpClient) {}

  listar(): Observable<any[]> {
    return this.http.get<any[]>(`${this.API}/listar`);
  }

  desvincular(id: number): Observable<any> {
    return this.http.put(`${this.API}/${id}/desvincular`, {});
  }
}
