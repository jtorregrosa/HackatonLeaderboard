/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HackatonLeaderboardTestModule } from '../../../test.module';
import { StageDetailComponent } from 'app/entities/stage/stage-detail.component';
import { Stage } from 'app/shared/model/stage.model';

describe('Component Tests', () => {
    describe('Stage Management Detail Component', () => {
        let comp: StageDetailComponent;
        let fixture: ComponentFixture<StageDetailComponent>;
        const route = ({ data: of({ stage: new Stage(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HackatonLeaderboardTestModule],
                declarations: [StageDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StageDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StageDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.stage).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
