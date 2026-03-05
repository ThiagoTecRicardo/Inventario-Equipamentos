import { OnInit } from '@angular/core';
import { EquipamentoService } from '../../services/equipamento';


export class EquipamentoListComponent implements OnInit {
  equipamentos: any[] = [];

  constructor(private service: EquipamentoService) {}

  ngOnInit(): void {
    this.carregar();
  }

  carregar() {
    this.service.listar().subscribe(dados => this.equipamentos = dados);
  }

  onDesvincular(id: number) {
    this.service.desvincular(id).subscribe(() => this.carregar());
  }
}
