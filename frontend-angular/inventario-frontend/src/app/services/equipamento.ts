import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})


export class Equipamento {

  private http = inject(HttpClient);
  private readonly API_URL = 'http://localhost:8080/api/equipamento/listar'; 

  listaEquipamentos = signal<Equipamento[]>([]);

  carregarTodos() {
    this.http.get<Equipamento[]>(this.API_URL).subscribe({
      next: (dados) => this.listaEquipamentos.set(dados),
      error: (err) => console.error('Erro ao carregar:', err)
    });
  }
}
