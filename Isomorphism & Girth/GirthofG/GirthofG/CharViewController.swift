//
//  CharViewController.swift
//  Girth
//
//  Created by 诸葛俊伟 on 10/23/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

import UIKit
import Charts

let SW = UIScreen.main.bounds.width
let SH = UIScreen.main.bounds.height

class CharViewController: UIViewController, ChartViewDelegate
{
    let testcase = TestCases()
    // calculate the time interval
    var start = Date(), end = Date(), timer:Double = 0.0
    
    // x
    let node = [20, 50, 100, 200, 500, 800, 1200, 1500, 2000, 3000, 5000]
    
    var graph: [[[Int]]] {
        return node.map({ (i: Int) -> [[Int]] in
            testcase.sparse(i)
        })
    }
    
    var girthTime: [Double] {
        var time = [Double]()
        for i in graph {
            start = Date()
            FindGirth(i)
            end = Date()
            timer = end.timeIntervalSince(start)
            time.append(timer)
        }
        return time
    }
    
    var chartView = LineChartView()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.createLineChartView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.setChart()
    }
    
    fileprivate func createLineChartView()
    {
        chartView = LineChartView.init(frame: CGRect(x: 0, y: 64, width: SW, height: SH-64))
        chartView.delegate = self
        chartView.backgroundColor = UIColor.white
        self.view.addSubview(chartView)
    }
    
    fileprivate func setChart() {
        let data = LineChartData()
        var de: [ChartDataEntry] = []
        for i in 0..<self.node.count {
            let dataEntry = ChartDataEntry(x: Double(self.node[i]), y: self.girthTime[i])
            de.append(dataEntry)
        }
        let ds = LineChartDataSet(values: de, label: "Girth")
        ds.colors = [UIColor.red]
        data.addDataSet(ds)
        
        self.chartView.data = data
        self.chartView.gridBackgroundColor = NSUIColor.white
        self.chartView.chartDescription?.text = "Girth of G"
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
