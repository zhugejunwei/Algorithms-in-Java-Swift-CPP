//
//  SparseGraphViewController.swift
//  Dijkstra & Floyd
//
//  Created by 诸葛俊伟 on 10/3/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

import UIKit
import Charts

class SparseGraphViewController: UIViewController, ChartViewDelegate
{
    // MARK: - Properties
    
    let testcase = TestCases()
    // calculate the time interval
    var start = Date(), end = Date(), timer:Double = 0.0
    
    // x
    let node = [10, 20, 40, 100, 200]
    
    // graphs
    var spars2D: [[[Int]]] {
        return node.map({ (i: Int) -> [[Int]] in
            testcase.sparse(i)
        })
    }
    var spars1D: [[Int]] {
        return node.map({ (i: Int) -> [Int] in
            testcase.sparse1D(i)
        })
    }
    
    var sparsLink: [Graph] {
        return node.map({ (i: Int) -> Graph in
            testcase.sparseLinkedList(i)
        })
    }
    
    // y
    
    // Dijkstra 2D Complete
    var dij2DTimeS: [Double] {
        var time = [Double]()
        for i in spars2D {
            start = Date()
            allPairDijkstra(i)
            end = Date()
            timer = end.timeIntervalSince(start)
            time.append(timer)
        }
        return time
    }
    
    var dij1DTimeS: [Double] {
        var time = [Double]()
        for i in spars1D {
            start = Date()
            allPairDijktra(i)
            end = Date()
            timer = end.timeIntervalSince(start)
            time.append(timer)
        }
        return time
    }
    
    var dijLinkTimeS: [Double] {
        var time = [Double]()
        for i in sparsLink {
            start = Date()
            allPairDijkstra(i)
            end = Date()
            timer = end.timeIntervalSince(start)
            time.append(timer)
        }
        return time
    }
    
    var floy2DTimeS: [Double] {
        var time = [Double]()
        for i in spars2D {
            start = Date()
            floyd2D(i)
            end = Date()
            timer = end.timeIntervalSince(start)
            time.append(timer)
        }
        return time
    }
    
    var floy1DTimeS: [Double] {
        var time = [Double]()
        for i in spars1D {
            start = Date()
            floyd1D(i)
            end = Date()
            timer = end.timeIntervalSince(start)
            time.append(timer)
        }
        return time
    }
    
    var floyLinkTimeS: [Double] {
        var time = [Double]()
        for i in sparsLink {
            start = Date()
            floydLinkedList(i)
            end = Date()
            timer = end.timeIntervalSince(start)
            time.append(timer)
        }
        return time
    }
    
    var chartView = LineChartView()
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)

        chartView.animate(yAxisDuration: 1.0, easingOption: .easeInBounce)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.createLineChartView()
        self.setChart()
        self.createRightBarButtonItem()
    }
    
    fileprivate func createRightBarButtonItem()
    {
        let buttonRight = UIButton.init(type: .custom)
        buttonRight.frame = CGRect(x: 0, y: 0, width: 40, height: 40)
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(customView: buttonRight)
        buttonRight.setTitle("Save", for: .normal)
        buttonRight.addTarget(self, action: #selector(SparseGraphViewController.save(_:)), for: .touchUpInside)
    }
    
    @objc fileprivate func save(_ sender: UIButton)
    {
        // 保存到相册
        let saved = chartView.save(to: "/Users/zhugejunwei/Documents/Xcode/Algorithms-in-Swift-CPP/Dijkstra & Floyd", format: .png, compressionQuality: 0.8)
        if saved {
            print("Saved")
        }
    }
    
    fileprivate func createLineChartView()
    {
        chartView = LineChartView.init(frame: CGRect(x: 0, y: 64, width: SW, height: SH-64))
        //        chartView.lineData
        chartView.delegate = self
        chartView.backgroundColor = UIColor.white
        self.view.addSubview(chartView)
    }
    
    fileprivate func setChart()
    {
        let data = LineChartData()
        
        // Dijkstra - 2D
        var de1: [ChartDataEntry] = []
        for i in 0..<node.count {
            let dataEntry = ChartDataEntry(x: Double(node[i]), y: dij2DTimeS[i])
            de1.append(dataEntry)
        }
        let ds1 = LineChartDataSet(values: de1, label: "Dij2D")
        ds1.colors = [UIColor.red]
        data.addDataSet(ds1)
        
        // Dijkstra - 1D
        var de2: [ChartDataEntry] = []
        for i in 0..<node.count {
            let dataEntry = ChartDataEntry(x: Double(node[i]), y: dij1DTimeS[i])
            de2.append(dataEntry)
        }
        let ds2 = LineChartDataSet(values: de2, label: "Dij1D")
        ds2.colors = [UIColor.blue]
        data.addDataSet(ds2)
        
        // Dijkstra - LinkedList
        var de3: [ChartDataEntry] = []
        for i in 0..<node.count {
            let dataEntry = ChartDataEntry(x: Double(node[i]), y: dijLinkTimeS[i])
            de3.append(dataEntry)
        }
        let ds3 = LineChartDataSet(values: de3, label: "DijLink")
        ds3.colors = [UIColor.yellow]
        data.addDataSet(ds3)
        
        // Floyd - 2D
        var de4: [ChartDataEntry] = []
        for i in 0..<node.count {
            let dataEntry = ChartDataEntry(x: Double(node[i]), y: floy2DTimeS[i])
            de4.append(dataEntry)
        }
        let ds4 = LineChartDataSet(values: de4, label: "Floy2D")
        ds4.colors = [UIColor.gray]
        data.addDataSet(ds4)
        
        // Floyd - 1D
        var de5: [ChartDataEntry] = []
        for i in 0..<node.count {
            let dataEntry = ChartDataEntry(x: Double(node[i]), y: floy1DTimeS[i])
            de5.append(dataEntry)
        }
        let ds5 = LineChartDataSet(values: de5, label: "Floy1D")
        ds5.colors = [UIColor.brown]
        data.addDataSet(ds5)
        
        // Floyd - Lined List
        var de6: [ChartDataEntry] = []
        for i in 0..<node.count {
            let dataEntry = ChartDataEntry(x: Double(node[i]), y: floyLinkTimeS[i])
            de6.append(dataEntry)
        }
        let ds6 = LineChartDataSet(values: de6, label: "FloyLink")
        ds6.colors = [UIColor.cyan]
        data.addDataSet(ds6)
        
        self.chartView.data = data
        
        self.chartView.gridBackgroundColor = NSUIColor.white
        
        self.chartView.chartDescription?.text = "Time Performance - Sparse Graph"
        
        // Color API
        // ChartColorTemplates.liberty()
        // ChartColorTemplates.joyful()
        // ChartColorTemplates.pastel()
        // ChartColorTemplates.colorful()
        // ChartColorTemplates.vordiplom()
    }
    
    fileprivate func chartValueSelected(chartView: ChartViewBase, entry: ChartDataEntry, dataSetIndex: Int, highlight: Highlight) {
        print("\(entry.y) in \(node[Int(entry.x)])")
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

